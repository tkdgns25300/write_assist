# Write Assist

**문맥 고민 끝! AI가 완벽한 글을 즉시 생성합니다.**

상황에 딱 맞는 글쓰기, 로그인 없이 1초 만에.

## 🚀 서비스 소개

Write Assist는 AI를 활용하여 사용자가 입력한 텍스트를 원하는 **톤, 목적, 분량, 스타일**에 맞게 교정해주는 웹 서비스입니다.

### 해결하는 문제
- "업무 메일 톤이 너무 딱딱하지 않을까?"
- "친한 동료에게 너무 격식 있게 말한 건 아닐까?"
- "이 내용을 어떻게 정중하게 전달하지?"

## ✨ 주요 기능

### 🎛️ 정교한 문맥 설정
- **톤 (Tone)**: 매우 친근함 ↔ 매우 정중함 (1-5단계)
- **목적 (Purpose)**: 정보 전달 / 설득·요청 / 사과·거절 / 감사·칭찬
- **분량 (Length)**: 짧게 / 표준 / 길게
- **스타일 (Style)**: 간결·명쾌 / 감성·자연스러움 / 전문·학술적

### 🎯 프리셋 기능
- **표준 업무 메일**: 정중한 톤으로 공식적인 내용 전달
- **친한 동료 대화**: 친근한 톤으로 캐주얼한 소통
- **정중한 요청/거절**: 매우 정중한 톤으로 어려운 내용 전달
- **보고서 초안**: 전문적인 스타일로 핵심만 요약

### 📝 사용법
1. **텍스트 입력**: 교정할 내용을 붙여넣기 (최대 1,000자)
2. **문맥 설정**: 4가지 설정 또는 프리셋 선택
3. **Generate & Copy**: AI가 다듬은 글을 바로 복사!

## 🛠️ 기술 스택

### Frontend
- **Next.js 14** - React 기반 풀스택 프레임워크
- **TypeScript** - 타입 안전성
- **Tailwind CSS** - 유틸리티 퍼스트 CSS 프레임워크
- **AWS Amplify** - 정적 웹사이트 호스팅

### Backend
- **Spring Boot** - Java 기반 REST API
- **Gradle** - 빌드 도구

## 🚀 배포

### Frontend (AWS Amplify)
```bash
# 프로젝트 루트에서
cd frontend/app
npm install
npm run build
```

### Backend (Spring Boot)
```bash
# backend 디렉토리에서
./gradlew build
./gradlew bootRun
```

## 📁 프로젝트 구조

```
write_assist/
├── frontend/
│   └── app/                 # Next.js 앱
│       ├── src/
│       │   ├── components/  # React 컴포넌트
│       │   └── lib/         # 유틸리티 및 API 로직
│       └── package.json
├── backend/                 # Spring Boot API
│   ├── src/main/java/
│   └── build.gradle
├── amplify.yml             # AWS Amplify 설정
└── README.md
```

## 💡 특징

- **무료 사용**: 로그인 없이 모든 기능 이용 가능
- **일일 제한**: IP/쿠키 기반 일일 사용 횟수 제한
- **광고 지원**: Google AdSense를 통한 서비스 운영
- **즉시 사용**: 별도 가입 없이 바로 시작

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

---

**Write Assist**로 더 나은 글쓰기를 시작해보세요! 🎉