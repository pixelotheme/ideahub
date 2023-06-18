#step3
# stop.sh : 기존 엔진엑스에 연결되어 있진 않지만, 실행중이던 프르이 부트 종료
# start.sh : 배포할 신규 버전 스프링 부트 프로젝트를 stop.sh를 통해 종료한 'profile'로 실행
# health.sh : 'start.sh'로 실행시킨 프로젝트가 정상적으로 실행됐는지 체크
# switch.sh : 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경
# profile.sh : 앞선 4개 스크립트 파일에서 공용으로 사용할 'profile'과 포트 체크 로직

# !/user/bin/env/ bash

# ABSDIR 현재 stop.sh 가 속해 있는 경로 찾기
# 하단의 코드와 같이 profile.sh의 경로를 찾기 위해 사용
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)

# java 의 import 구문 과 같다
# 해당 코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용할수 있다
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh


IDLE_PORT=$(find_idle_port)


echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://location:$IDLE_PORT/profile "

sleep 10

for RETRY_COUNT in {1..10}
do
  # 엔진엑스와 연결되지 않은 포트로 스프링부트가 잘 수행 되었는지 학인, 응답값을 HttpSatus로 받는다
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -1) # real 문자열 가져오기

  # $UP_COUNT >= 1, REAL 문자열 있는지 검증
  if [ ${UP_COUNT} -ge 1 ]
  then
    echo "> Health check 성공"
    switch_proxy
    break
  else
    echo "> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다."
    echo "> Health check: ${RESPONSE}"
  fi

  if [ {$RETRY_COUNT} -eq 10 ]
  then
    echo "> HEALTH check 실패"
    echo "> 엔진엑스에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10

done
