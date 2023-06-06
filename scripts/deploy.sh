# step2 환경에서 실행될 deploy.sh 새엇ㅇ

#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=ideahub

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"

#현재 수행 중인 스프링 부트 애플리케이션 프로세스 id 찾기
#실행 중이면 종료하기 위함
#스프링 부트 애플리케이션 이름(ideahub)으로 된 다른 프로그램들이 있을수 있어
#ideahub로 된 jar(pgrep -fl ideahub | grep jar )
# 프로세스를 찾은뒤 ID를 찾는다 (| awk '{print $1}')
CURRENT_PID=$(pgrep -fl ideahub | grep jar | awk '{print $1}')

echo "> 현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> Kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

#Jar 파일은 실행권한이 없는 상태 -> 실행 권한을 부여한다
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    -Dspring.config.additional-location=classpath:/application.yml,/home/ec2-user/app/application-real-db.yml,/home/ec2-user/app/application-real-oauth.yml \
    -Dspring.profiles.active=real1 \
    #nohup 실행시 CodeDeploy는 무한 대기중
    #-> 이슈 해결위해 nohup.out파일을 표준 입출력용으로 별도로 사용
    #이렇게 하지 않으면 nohup.out파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력된다
#    nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 해야만 한다
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
