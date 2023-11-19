package com.trip.member;

import com.trip.member.service.MemberService;
import com.trip.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // default maxAge = 1800 (30min)
@RequestMapping("/member")
@RequiredArgsConstructor
public class RestMemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;


    /**
     * 필터 / 인터셉터로 먼저 header에 Authorization있나 검사하고 호출하게 됨!
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody MemberDto loginUser) throws Exception {
        log.debug(loginUser.getUserId());
        log.debug(loginUser.getUserPass());

        MemberDto foundUser = memberService.login(loginUser.getUserId(), loginUser.getUserPass());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if(foundUser != null) {
            String accessToken = jwtUtil.createAccessToken(foundUser.getUserId());
            String refreshToken = jwtUtil.createRefreshToken(foundUser.getUserId());
            log.debug("doLogin(); access token: {}", accessToken);
            log.debug("doLogin(); refresh token: {}", refreshToken);

            // 발급받은 refresh Token DB 저장
            memberService.saveRefreshToken(foundUser.getUserId(), refreshToken);

            // JSON으로 토큰 전달
            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);

            // Q. 그냥 이 시점에 유저 정보 보내면 안되나?
            // A. 누가 중간에 탈취해서 그사람이 토큰 + 해당 유저 정보 다 가져갈 수 있을 것 같아서 그럼 안되겠는데?
            // resultMap.put("userInfo", foundUser);

            status = HttpStatus.CREATED;
        } else {
            resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    /**
     * 유효한 JWT 토큰을 바탕으로 해당 회원을 조회해 반환한다.
     * @param userId
     * @param authentication
     * @return
     * @throws SQLException
     */
    @GetMapping("/validate/{userId}")
    public ResponseEntity<?> doValidate(@PathVariable String userId, Authentication authentication) throws SQLException {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

        String userFromTokenBody = authentication.getName();

        log.debug("params : {}", userId);
        log.debug("userFromTokenBody : {}", userFromTokenBody);

        if(!userId.equals(userFromTokenBody)) {
            status = HttpStatus.UNAUTHORIZED;
            return ResponseEntity.status(status).build();
        }

        // 매번 DB 접근??
        Optional<MemberDto> member = memberService.findByUserId(userId);
        if(!member.isPresent()) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).build();
        }

        resultMap.put("userInfo", member.get());
        return new ResponseEntity<>(resultMap, status);
    }


    /**
     * 클라이언트에서 로그아웃 요청을 보낸다.
     * 서버는 유효한 요청에 대해서만 DB 서버에 접근해 refreshToken을 초기화한다.
     * 그렇지 않은 경우 클라이언트에게는 Accepted만 보내준다.
     * @param userId
     * @param authentication
     * @return HttpStatus.ACCEPTED, HttpStatus.OK
     */
    @GetMapping("/logout/{userId}")
    public ResponseEntity<?> doLogout(@PathVariable String userId, Authentication authentication) {
        HttpStatus status = HttpStatus.ACCEPTED;
        String userFromTokenBody = authentication.getName();
        log.debug("userId : {}, token : {}", userId, userFromTokenBody);
        if(userId.equals(userFromTokenBody)) {
            try {
                memberService.deleteRefreshToken(userId);
                status = HttpStatus.OK;
                log.debug("뀨2");
            } catch(SQLException e) {
                log.error("doLogout(), delete refresh Token failed");
                log.debug("뀨3");
            }
        }

        return new ResponseEntity<>(status);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, @RequestHeader String refreshToken) throws SQLException {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        log.debug("refreshToken : {}", refreshToken);

        if(jwtUtil.isExpired(refreshToken)) {
            if(refreshToken.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
                String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
                log.debug("정상적으로 accessToken 재발급");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("refresh 토큰도 사용 불가!");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(resultMap, status);
    }

}
