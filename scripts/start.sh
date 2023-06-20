#step3
# stop.sh : 기존 엔진엑스에 연결되어 있진 않지만, 실행중이던 프르이 부트 종료
# start.sh : 배포할 신규 버전 스프링 부트 프로젝트를 stop.sh를 통해 종료한 'profile'로 실행
# health.sh : 'start.sh'로 실행시킨 프로젝트가 정상적으로 실행됐는지 체크
# switch.sh : 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경
# profile.sh : 앞선 4개 스크립트 파일에서 공용으로 사용할 'profile'과 포트 체크 로직


# 기본적인 스크립트는 step2 의 deploy.sh와 유사
# 다른점
# IDLE_PROFILE을 통해 yml 파일을 가져오고 (application-deploy-$IDLE_PROFILE.yml)
# active profile을 지정하는 것뿐
# IDLE_PROFILE은 profile.sh 에 정의되어있다

# !/user/bin/env/ bash

ABSPATH=$(readlink -f $0) # $0 = 상대경로로 현재 파일의 위치가 나온다, $(readlink -f $0) 절대경로로 현재 파일의 위치가 나온다
ABSDIR=$(dirname $ABSPATH) # dirname : 디렉토리 경로 출력 (마지막 경로 제외 출력 home/ec2-user/app/step3 -> home/ec2-usr/app 까지만 출력)
source ${ABSDIR}/profile.sh # import와 같다


REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=ideahub

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
# ls -tr : 파일 수정한 시간순의 역순 으로 정렬 - 최근것이 맨아래
#  tail -n 으로 가장 나중의 jar 파일(최신 파일)을 변수에 저장한다
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행"

#    -Dspring.config.additional-location=classpath:/application.yml,classpath:/application-deploy-$IDLE_PROFILE.yml,/home/ec2-user/app/application-real-db.yml,/home/ec2-user/app/application-real-oauth.yml \
nohup java -jar \
    -Dspring.config.additional-location=classpath:/application-deploy-$IDLE_PROFILE.yml,/home/ec2-user/app/application-real-db.yml,/home/ec2-user/app/application-real-oauth.yml \
    -Dspring.profiles.active=$IDLE_PROFILE \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
