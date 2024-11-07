
### ConvenienceStoreHeadOffice: 편의점 본사

- SuppliedItemInventory <'Item'>: List, 납품되는 상품 저장소
- ConvenienceStoreInventory <'ConvenienceStore'>: List


- Item(Parent 클래스)
  - ItemName: String
  - ItemPrice: long
  - OngoingPromotion<'Promotion'>: List, null 허용


- MembershipPolicy: enum, 멤버십 정책
  - 할인율 30%
  - 최대 할인 가능 금액 8,000원
  - 할인 금액 계산 로직


- PromotionPolicy: 프로모션 정책(N+1)
  - promotionName: String - 프로모션 이름
  - numberOfItemToGet: int - N
  - numberOfItemToBuy: int - 1(고정)
  - startDate: Datetime - 프로모션 시작 기간
  - endDate: Datetime - 프로모션 종료 기간

<br>


### PaymentMachine: 결제 시스템

- View
- Formatter
- Parser
- Validator

<br>

### ConvenienceStore: 편의점 지점

- Item(Child 클래스)
  - itemQuantity: int, 제품 수량
  - ExpiryDate: Datetime, 유통기한

- ItemInventory: 상품 재고 정보를 담은 저장소
- Revenue: 수익 관련 정보





