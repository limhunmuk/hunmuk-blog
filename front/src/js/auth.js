function isTokenExpired(_token) {
  if (!_token) return true; // 토큰이 없으면 유효하지 않음

  const payloadBase64 = _token.split('.')[1]; // JWT는 "헤더.페이로드.서명" 형식
  const decodedPayload = JSON.parse(atob(payloadBase64));

  const exp = decodedPayload.exp * 1000; // `exp`는 초 단위이므로 밀리초로 변환
  const currentTime = Date.now();

  return currentTime > exp; // 현재 시간이 만료 시간보다 크면 만료됨
}
