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

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"

    # "set \$service_url http://127.0.0.1:${IDLE_PORT};"
    # 하나의 문장을 만들어 파이프라인 (|)으로 넘겨주기 위해 echo를 사용
    # 엔진엑스가 변경할 프록시 주ㅗ 생성
    # 쌍따옴포를 사용해야 한다 ""
    # 쌍따옴포를 하지 않으면 $service-url을 그대로 인식하지 못하고 변수를 찾게 된다
    # ----------
    #  | sudo tee /etc/nginx/conf.d/service-url.inc
    # 앞에서 넘겨준 문장을 service-url.inc에 덮어쓴다
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    # 엔진엑스 설정을 다시 불러온다
    # restart와는 다르다
    # restart: 잠시 끊기는 현상 O , reload: 끊김 없이 다시 불러온다
    # 다만 중요한 설정들은 반영되지 않으므로 restart를 사용해야 한다
    # 여기선 외부의 설정 파일인 service-url을 다시 불러오는 거라 reload로 가능
    sudo service nginx reload
}