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


IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -it tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."

else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi
