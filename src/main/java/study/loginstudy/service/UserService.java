package study.loginstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.loginstudy.domain.dto.JoinRequest;
import study.loginstudy.domain.dto.LoginRequest;
import study.loginstudy.domain.entity.User;
import study.loginstudy.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // 비밀번호 암호화하는 클래스
    private final BCryptPasswordEncoder encoder;


    // loginId 중복 체크, 회원가입 구현 시 사용, 중복되면 true return
    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    // nickname 중복 체크, 회원가입 기능 구현 시 사용, 중복되면 true return
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    // 이 로직을 사용할려면 JoinRequest에 toEntity에 매개변수가 없는것도 만들어야 된다.
    // 비밀번호 암호화 안하고 회원가입 로직
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
    }


    // 비밀번호 암호화 하고 회원가입 로직
    public void join2(JoinRequest req) {
        userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    }


    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(req.getPassword())) {
            return null;
        }
        return user;
    }

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if (optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }


}
