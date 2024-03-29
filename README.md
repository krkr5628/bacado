# 현재상황(재구성 진행)
- ChaGPT 4.0에서 데이터 분석 및 시각화 기능이 제공되는데, 그러면 받아온 재무표에서 재무 도메인을 모아서 중복 제거를 한 후, 표준화에 도움을 얻을 수 있게 되었다.
- 단, 맞는지 확인하기 위한 절차가 필수적이다.
- 겸사 겸사 SEC 보고서에 대한 재무표를 받아올 방법도 생각해서 한번에 실행해보자.
- World Qaunt의 컨셈을 참고하여
  
# BACADO : 증권 가치 평가 서비스
❓ Problem1 : 애널리스트들이 분석 자료를 올리지만 어떠한 방식으로 계산하는지 알 수 없다.

❓ Problem2 : 유명한 가치 평가 공식이 많지만 복잡하거나 이해하기 어려워 직접 사용하기 힘들다.

‼ Idea1 : 이론적으로 혹은 알려진 가치 평가식을 설명해주고 숫자 입력만으로 자신만의 가치평가를 가능하게 하자.

‼ Idea2 : 사용자들이 다양한 가치평가 식을 접하고 배울수 있도록 만들다.

💯 Solution : 원본 재무제표 표준화를 통해서 쉽게 활용할 수 있도록 만들자.

# 상세 정보
https://seongyeopchoi.notion.site/Bacado-Share-55da8163d33a42dab752973e4c0e996b?pvs=4

# 사용기술
- JAVA, Intellij, Github

# 사용 API
- open_dart : 고유번호, 단일회사 전체 재무제표(금융업 제외), 
- krx_open_api : 유가증권 종목기본정보, 코스닥 종목기본정보, 유가증권 일별매매정보, 코스닥 일별매매정보, KRX 시리즈 일별시세정보, KOSPI 시리즈 일별시세정보, KOSDAQ 시리즈 일별시세정보

# 참고 회사
- Bloomberg, REFINITIV ELKON, S&P CAPITAL IQ PLATFORM, Koyfin, TradingView, 연합인포맥스 => 정보 분석 프로그램 웹 기반 필수
- World Qaunt Brain => 대중에게 공개하여 퀀트식을 받고 성능에 따라 일정 커미션 제공

# 구조
![image](https://github.com/krkr5628/bacado/assets/75410553/b57df649-f886-44f2-b7ce-cc073e2e7093)

# 특징
- 웹기반 프로그램
- 한국, 미국, 중국, 일본, 홍콩, 영국, 독일, 프랑스, 네덜란드 등 다양한 국가 정보
- API 기반 거래 프로그램 연동

# 기본 기능
- 재무제표 분기 갱신, 가격 실시간 갱신
- 주요 경제 지표 및 일정 갱신
- 종목별 배당, 유무상, 권리락 등 일정 갱신
- 가치 투자 공식 및 간편 계산 기능
- 테마 정리
- 커뮤니티

# 특별 기능
- 다양한 가치 및 투자 지표 모든 값 실시간 계산 반영
- 기본 재무 정보 및 위성 사진과 같은 특수 정보 제공
- 백테스팅
- 조건 검색(Refresh 1s, 5s, 10s, 1m..)
- 증권사 레포트 요약 기능
- 카카오톡 및 텔레그램을 통한 주요 정보 알림 서비스
- 개인 계좌 연동(API)

# 커뮤니티 기능(World Qaunt Brain)
- 개인들의 분석 기법 공유(TradingVidw 유사)
- 매주-월-분기-반기 기준 대회 진행
- 개인형 펀드 구성 기능
  
# 수익화
- 백테스팅(건별)
- API 형식 데이터 판매(개별)
- 개인 전문가 종목 추천(증권 플러스)
- 개인 퀀트 계약 및 커미션 제공(World Quant Brain, 비공개형)
- 연금 및 개인형 ETF와 펀드 생성 각 1개(공개형)

# 모듈1 : 종목 코드 및 재무제표 자동 갱신
- main
- initial.initial_update : corp_code와 ISU_SRT_CD 매칭 후 저장
- initial.code.dart_code_update : open_dart의 재무제표를 위한 zip 파일 corp_code 수집 후 csv 저장
- initial.code.short_code_update : 종목 리스트 및 가격 확인을 위한 ISU_SRT_CD 수집 후 저장
- initial.code.update_status : 업데이트 현황 파악
- initial.code.update_list : 업데이트 필요한 종목만 분리하여 저장
- initial.code.code_integration : dart코드 short코드 매칭
- initial.price.index_update : 코스피200과 같은 주요 인덱스 가격 업데이트
- initial.price.price_update : dart_code를 통해 재무제표를 받고 필요한 부분만 받아서 list 추출
- initial.index.commodity_series_update : 선물 옵션 가격 업데이트
- initial.price_update : 종목 일봉 업데이트
- initial.check_update : 당일 어떤 항목을 언제 업데이트 했는지 기록
- load_save.CSV : csv 파일 로드 및 저장, 경로지정, 파일명 지정, 파일 지정
- load_save.ListToHashMap : 리스트 ⇒ hashmap 변경
- tools.make_directory : 신규 폴더 생성
- tools.file_name_change : 파일명 변경
- caculation : 가치 계산 모음

# 모듈2 : 재무제표 맵핑

# 모듈3 : 기술 지표 계산

# 모듈4 : 가치 평가 계산

# 파일1(scv) : 종목코드 업데이트 및 맵핑
- dart_code : open_dart corp_code 원본 파일(영업일 마다 업데이트)
- kospi_code : krx kospi short_code 원본 파일(영업일 마다 업데이트, 기존 파일에 신규 코드만 추가)
- kosdak_code : krx kosdak short_code 원본 파일(영업일 마다 업데이트, 기존 파일에 신규 코드만 추가)
- kospi_integration : kospi corp_code와 short_code 연결되어 정리된 파일(비매칭 현황 직접 수정)
- kosdak_integration : kosdak corp_code와 short_code 연결되어 정리된 파일(비매칭 현황 직접 수정)
- kospi_left : kospi 맵핑불가능한 회사 목록(영업일 확인 후 업데이트)
- kosdak_left : kosdak 맵핑불가능한 회사 목록(영업일 확인 후 업데이트)
  
# 파일2(scv) : 재무제표 현황 업데이트(2012년 ~ 자료)
- kospi_status : 재무제표 업데이트 현황 및 비금융회사 구분 목록
- kosdak_status : 재무제표 업데이트 현황 및 비금융회사 구분 목록
- kospi_update_list : 재무제표 업데이트가 필요한 회사 목록
- kosdak_update_list : 재무제표 업데이트가 필요한 회사 목록
- update_log : API를 통해 외부에서 받아오는 함수에 대한 요청 일자 기록(1일 1회 요청만 하기 위함)

# 파일3(CSV) : 재무제표 목록(2012년 ~ 자료)
