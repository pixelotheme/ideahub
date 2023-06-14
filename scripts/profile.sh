# step3 환경에서 실행

#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용 중이면 real2가 쉬고 있고 반대면 real1이 쉬고 있음
function find_idle_profile(){
#  형재 엔진엑스가 바로보고 ㅣㅇㅆ는 스프링 부트가 정상적으로 수행 중인지 ㅗ학인
#  응답값을 HttpSatus로 받는다
#  정상 : 200, 오류 : 400~503 사이 발생시 오류, 모두 예외로 보고 real2를 현재 profile로 사용
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ]

  then
    CURRENT_PROFILE=real3
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  # IDLE_PROFILE
  # 엔진엑스와 연결되지 않은 profile
  # 스프링 부트 프로젝트를 이 profile로 연결하기 위해 반환
  if [ ${CURRENT_PROFILE} == real2 ]
  then
    IDLE_PROFILE=real3
  else
    IDLE_PROFILE=real2
  fi
  # bash라는 스크립트는 값을 반환하는 기능이 없다
  # 그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서  $(find_idle_profile) 사용
  # 중간에 echo를 사용해서는 안된다
  echo "${IDLE_PROFILE}"

}

# 쉬고있는 profile의 port 찾기
function find_idle_port(){
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real2 ]
   then
     echo "8002"
   else
    echo "8003"
  fi
}