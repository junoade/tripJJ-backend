package com.trip.member;

import com.trip.member.service.MemberService;
import com.trip.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> doLogin(@RequestBody Map<String, Object> map) throws Exception {
        log.debug(map.get("id").toString());
        log.debug(map.get("password").toString());

        String id = (String) map.get("id");
        String password = (String) map.get("password");

        MemberDto hasFound = memberService.login(id, password);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if(hasFound != null) {
            String accessToken = jwtUtil.createAccessToken(hasFound.getUserId());
            String refreshToken = jwtUtil.createRefreshToken(hasFound.getUserId());
            log.debug("doLogin(); access token: {}", accessToken);
            log.debug("doLogin(); refresh token: {}", refreshToken);

            // 발급받은 refresh Token DB 저장
            memberService.saveRefreshToken(hasFound.getUserId(), refreshToken);

            // JSON으로 토큰 전달
            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);
            status = HttpStatus.ACCEPTED;
        } else {
            resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(resultMap, status);
    }


}
