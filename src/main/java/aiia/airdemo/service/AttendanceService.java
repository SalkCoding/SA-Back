package aiia.airdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceService {

    //Todo: 출석 코드 생성

    //Todo: 특정 요일에 출석할 유저 지정

    //Todo: 특정 요일에 출석한 유저 조회

    //Todo: 출석 코드 확인

    //Todo: 출석 여부 수동 변경

}
