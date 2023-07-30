#재개발 예정

#문제
1. Amazon LightSail Linux UI => 업데이트 현황 오류 현황 확인용
2. 재무제표 업데이트 일정
3. 재무제표 매핑의 한계

# bacado
1. 목적
- 웹기반 유명 가치 투자 기법 자동 계산
- 웹기반 백테스팅 시스템
- 웹기반 포트폴리오
- 커뮤니티 발굴

2. 참고 회사
- 주요 HTS
- 블룸버그 터미널, REFINITIV ELKON, S&P CAPITAL IQ PLATFORM
- 퀀트 킹
- Koyfin, TradingView
- 더 리치 - [https://www.therich.io/](https://www.therich.io/) (배당 컨셉 - TradingView 위주)

3. 형태
- 웹앱 : 카페24, MariaDB / 아마존 라이트세일, MySQL
- Notion
- Twitter, Telegram

4. 주요 항목
- Tradingview API
- 국내
- ~~미국 Nyse, Nasdaq : SEC?~~
- ~~일본, 독일, 영국, 프랑스, 인도, 베트남 등 ⇒ FMD?~~

5. 주요 기능
- 업데이트 : 주요 시장 별 1일 1회 주가 갱신, 재무제표 분기 갱신
- Tradingview를 통한 실시간 가격  및 차트
- **주요 경제 지표 및 일정**
- **주요 국가별 종목별 배당, 유무상, 권리락 등 일정**
- **수 많은 가치 투자 공식**
- **백테스팅**
- api 연동을 통한 계좌 정보 확인 및 거래(증권 플러스와 유사 / 브로커)
- 조건 검색
- 테마 정리
- 다크 모드 온오프
- 증권 레포트와 유사한 사이트 모형
- **주요국 재무제표, 경제지표, 가치투자 결과를 API로 송출**

6. 커뮤니티 기능(정확한 정보와 분석이 우선시)
- 개인들의 분석 기법 공유 및 실제 항목으로 활용(TradingVidw 유사)
- 개인간 차트 분석, 재무재표 분석, 예측 주가 언급
- 개인간 추천 종목 리스트(증권 플러스)
- 개인 포트폴리오 연동
- 트위터 및 텔레그램을 통한 주요 정보 자동 업데이트

7. 파일(src)
(back_end) 자료 수집, 분석 자동화, 데이터베이스 관리
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
(frond_end)
- Amazon LightSail
- WordPress(검토중)

8. 파일(scv)
(list)
- dart_code : open_dart corp_code 원본 파일(영업일 마다 업데이트)
- kospi_code : krx kospi short_code 원본 파일(영업일 마다 업데이트, 기존 파일에 신규 코드만 추가)
- kosdak_code : krx kosdak short_code 원본 파일(영업일 마다 업데이트, 기존 파일에 신규 코드만 추가)
- kospi_integration : kospi corp_code와 short_code 연결되어 정리된 파일(비매칭 현황 직접 수정)
- kosdak_integration : kosdak corp_code와 short_code 연결되어 정리된 파일(비매칭 현황 직접 수정)
- kospi_left : kospi 매칭불가능한 회사 목록(영업일 확인 후 업데이트)
- kosdak_left : kosdak 매칭불가능한 회사 목록(영업일 확인 후 업데이트)
- kospi_status : 재무제표 업데이트 현황 및 비금융회사 구분 목록
- kosdak_status : 재무제표 업데이트 현황 및 비금융회사 구분 목록
- kospi_update_list : 재무제표 업데이트가 필요한 회사 목록
- kosdak_update_list : 재무제표 업데이트가 필요한 회사 목록
- update_log : API를 통해 외부에서 받아오는 함수에 대한 요청 일자 기록(1일 1회 요청만 하기 위함)

(kospi) 유가증권 2012 ~ 재무제표, 가격지표 (Dart_code 순으로 정리)
(kosdak) 코스닥 2012 ~ 재무제표, 가격지표 (Dart_code 순으로 정리)
(index) 주요 인덱스 2012 ~ 가격지표

9. 향후
- 클라우드 플랫폼을 통한 데이터 분석
- API or Excel을 통한 자료 판매
