# 현재상황(개발중단-재구성 진행)
- 재무제표가 가진 지표들을 표준화된 양식으로 매핑하기 힘들다.
- 회사별로 지표를 부르는 이름이 다르고 계산식도 조금씩 다르다.
  
# BACADO : 증권 가치 평가 서비스
❓ Problem1 : 애널리스트들이 분석 자료를 올리지만 어떠한 방식으로 계산하는지 알 수 없다.

❓ Problem2 : 유명한 가치 평가 공식이 많지만 복잡하거나 이해하기 어려워 직접 사용하기 힘들다.

‼ Idea1 : 이론적으로 혹은 알려진 가치 평가식을 설명해주고 숫자 입력만으로 자신만의 가치평가를 가능하게 하자.

‼ Idea2 : 사용자들이 다양한 가치평가 식을 접하고 배울수 있도록 만들다.

💯 Solution : 원본 재무제표 표준화를 통해서 쉽게 활용할 수 있도록 만들자.

# 사용기술
- JAVA, Intellij, Github

# 사용 API
- open_dart : 고유번호, 단일회사 전체 재무제표(금융업 제외), 
- krx_open_api : 유가증권 종목기본정보, 코스닥 종목기본정보, 유가증권 일별매매정보, 코스닥 일별매매정보, KRX 시리즈 일별시세정보, KOSPI 시리즈 일별시세정보, KOSDAQ 시리즈 일별시세정보

# 참고 회사
- Bloomberg, REFINITIV ELKON, S&P CAPITAL IQ PLATFORM
- 퀀트 킹, Koyfin, TradingView, 연합인포맥스

# 구조
![image](https://github.com/krkr5628/bacado/assets/75410553/b57df649-f886-44f2-b7ce-cc073e2e7093)

# 특징
- 웹기반 프로그램
- 한국, 미국, 중국, 일본, 홍콩, 영국, 독일, 프랑스, 네덜란드 등 다양한 국가 정보
- 웹 - 모바일 하이브리드 형태
- API 기반 거래 프로그램 연동

# 기본 기능
- 재무제표 분기 갱신, 가격 실시간 갱신
- 주요 경제 지표 및 일정 갱신
- 종목별 배당, 유무상, 권리락 등 일정 갱신
- 가치 투자 공식 및 간편 계산 기능
- 테마 정리
- 커뮤니티

# 특별 기능
- 가치 투자 공식 및 간편 계산 기능
- 백테스팅
- 조건 검색(Refresh 1s, 5s, 10s, 1m..)
- 증권사 레포트 요약 기능
- 카카오톡 및 텔레그램을 통한 주요 정보 알림 서비스
- 데이터 분석 기능

# 커뮤니티 기능
- 개인들의 분석 기법 공유(TradingVidw 유사)
- 개인 포트폴리오 연동(API)
- 매주 주가 혹은 지수 예측 대회 + 실전 투자 대회
  
# 수익화
- 백테스팅(개별)
- API 데이터 판매(개별)
- 커스텀 알림(이용권)
- 개인 전문가 종목 추천(증권 플러스)
- 투자 일임 회사 전용 서비스(증권 플러스)

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
