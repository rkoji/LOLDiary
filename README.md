# LOL-Diary
LOL-Diary는 **League of Legends** 플레이어들이 게임 일기를 작성하고 분석할 수 있는 **웹 애플리케이션**입니다.

## 기술 스택
- **프로그래밍 언어** : Java 17
- **프레임워크** : Spring Boot 3.2.3
- **데이터베이스** : MySQL 8.0
- **프론트엔드** : Thymeleaf, HTML, CSS
- **API 문서화** : Swagger (Springdoc OpenAPI)
- **빌드 도구** : Gradle

## 주요 기능
- **회원가입**: 새로운 사용자가 계정을 생성할 수 있습니다.
- **다이어리 관리**: 게임 플레이에 대한 일기를 작성, 삭제, 조회할 수 있습니다.
- **로그인 및 로그아웃**:사용자 인증 및 세션 관리를 통해 안전한 접근을 보장합니다.
- **게임 플레이 정보 조회**: 사용자별 게임 플레이 데이터를 조회하고 분석할 수 있습니다.

## API 명세서
### Auth
| **엔드포인트**             | **메서드**  | **설명**              |
|---------------------------|-------------|-----------------------|
| /members/signup           | `GET`       | 사용자 회원가입 화면 |
| /members/signup           | `POST`      | 사용자 회원가입       |

### 게임 플레이 정보
| **엔드포인트**             | **메서드**  | **설명**                      |
|---------------------------|-------------|--------------------------------|
| /match/list               | `GET`       | 날짜 조회 화면                |
| /match/list               | `POST`      | 게임 플레이 리스트 조회       |
| /match/summoner           | `GET`       | 사용자 정보 조회              |

### 다이어리 관리
| **엔드포인트**             | **메서드**  | **설명**                      |
|---------------------------|-------------|--------------------------------|
| /diary                    | `GET`       | 다이어리 작성 화면            |
| /diary                    | `POST`      | 다이어리 작성                 |
| /diary/update/{id}        | `POST`      | 다이어리 수정                 |
| /diary/{id}               | `GET`       | 다이어리 조회                 |
| /diary/edit/{id}          | `GET`       | 다이어리 수정 화면            |
| /diary/delete/{id}        | `GET`       | 다이어리 삭제                 |

### 로그인 및 로그아웃
| **엔드포인트**             | **메서드**  | **설명**                      |
|---------------------------|-------------|--------------------------------|
| /login                    | `GET`       | 로그인 화면                   |
| /login                    | `POST`      | 로그인                        |
| /logout                   | `POST`      | 로그아웃                      |
