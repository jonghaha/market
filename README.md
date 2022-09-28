### 개발 필수요건
1. 상품관리
- 상품을 등록 / 수정 / 삭제 할 수 있어야 한다.

2. 주문관리
- 등록한 상품으로 주문을 받을 수 있어야 한다.
- 주문한건당 여러개의 상품을 받을 수 있다.(1:N)
- 주문은 부분취소가 가능하다.
- 주문 내 일부 상품만 취소할 수 있다.
- 주문상태 예시 : 주문접수/배송완료/주문취소
- 주문 및 취소 시 알림
- 콘솔에 로그 출력을 알림발송으로 인정합니다.

### 동작 방법 및 설명
1. 상품 등록
- http://localhost:8080/product/insert
- Request JSON 예  
{"name": "상품1", "price": 11000, "description": "상품1설명", "imagesUrl":["https://test.com/1","https://test.com/2"]}
- Response  
result - 등록 성공 시 true 실패시 false  
data - 등록된 상품 정보

2. 상품 수정
- http://localhost:8080/product/update
- Request JSON 예  
  {"name": "상품1", "price": 11000, "description": "상품1설명"}
- Response  
result - 업데이트 성공 시 true 실패시 false  
data - 수정된 상품 정보

3. 상품 삭제
- http://localhost:8080/product/delete
- Request JSON 예  
  {"id": 1}
- Response  
result - 삭제 성공 시 true 실패시 false
- 삭제시 등록된 이미지도 같이 지워짐

4. 상품 주문
- http://localhost:8080/order
- Request JSON 예  
  {"memberId": "1", "address":{"zipCode": "1212", "address1": "주소1"}, "orderProducts":[{"productId": 1, "quantity": 10}, {"productId": 2, "quantity": 2}]}
- Response
result - 상품 주문 성공 시 true 실패시 false  
data - 등록된 주문 데이터  
- 주문 완료 알림 이벤트를 발행하여 처리. -> 알림발송에서 exception이 발생할 경우 주문 완료 건이 롤백처리되기 때문에

5. 상품 취소
- http://localhost:8080/order/cancel
- Request JSON 예  
{"orderId": 1, "memberId": 1}
- Response  
result - 주문 취소 성공 시 true 실패시 false  
data - 취소된 주문 데이터
- 주문 취소 알림 이벤트 발행하여 처리.