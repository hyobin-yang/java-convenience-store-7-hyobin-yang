# [Week 4] 편의점 🏪


## 🔔딸랑딸랑🔔<br>
### W 편의점 개업을 축하합니다🎊
### W 편의점의 새 점장님을 위한 몇 가지 안내 사항을 전달합니다.

### 이는 본사의 지침에 따른 것이며 운영 전에 반드시 모두 숙지하시는 것을 권고드립니다.

---

## 계산 절차


점장님이 계산 시작 버튼을 누르시면 안내문구와 함께 ‘현재 보유 상품’이 출력됩니다.

### [예시]

안녕하세요. W편의점입니다.<br>
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
---

그리고 구매 정보 입력 요청 문구가 출력됩니다.

`구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])`

그럼 고객이 가져온 상품을 확인하고 그 이름과 수량을 점장님께서 직접 입력해주시면 됩니다☺️

입력 형식은 위 예시와 같습니다.

- 여러 상품을 입력할 때는 쉼표(,)로 구분해주세요. 쉼표가 아닌 구분자 입력 시 ERROR
- 상품명은 한글, 영어, 숫자, 특수기호가 포함될 수 있습니다.
- 수량은 반드시 1 이상의 정수로, 그리고 각 상품의 재고 수량 내로 입력해주세요.
- 대괄호와 상품명, -, 수량 사이에는 공백이 포함될 수 있습니다. 공백은 결제 시스템이 직접 처리합니다. 😎
- 위의 형식을 지키지 않을 시 ERROR가 발생하고 이 경우에 처음 입력으로 돌아갑니다.
- 만약 존재하지 않는 상품을 입력했을 경우에도 처음 입력으로 돌아갑니다.
- 구매할 상품을 다시 입력해야 하는 경우는 재고를 ⛔️다시 보여드리니 ⛔️ 상품명과 수량을 잘 확인한 후 입력해주세요.

## 프로모션 상품 관련 안내

- 프로모션 기간이 지난 상품은 본사 처리에 따라 상품 리스트에서 제거되며 기존 프로모션 재고는 일반 재고로 이월됩니다. 즉 프로모션 상품은 리스트에서 제거되고 일반 재고의 수량은 기존 프로모션 수량만큼 더해집니다.
- 그러나 이는 본사의 업데이트 날짜에 따라 일괄적으로 이월되므로 프로모션 간혹 프로모션 날짜 내에 있지 않더라도 이월되지 않은 상태로 재고가 출력될 수 있습니다.
- 그러나 걱정하지 않으셔도 됩니다. 오늘을 기준으로 프로모션 기간이 지난 경우, 혹은 프로모션 기간 전인 경우에는 프로모션 가격으로 결제되지 않습니다. 그러니 그대로 결제를 진행해주시면 됩니다.👍
- 프로모션이 적용되는 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우 이 혜택을 잊지 않도록 시스템이 알려줍니다. 아래 예시처럼요!
- 동일한 상품에 여러 프로모션이 적용되는 경우는 없습니다.
- 프로모션 상품이 존재하는데 일반 상품이 존재하지 않는 경우는 없습니다.

`현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다.추가하시겠습니까? (Y/N)`

- 고객 요청에 따라 추가할지 여부를 입력해주시면 됩니다.
- 입력 형식은 추가할 경우 Y, 추가하지 않을 경우 N이며 소문자(y,n)도 가능합니다.
- 이외의 입력값은 허용하지 않으며, 마찬가지로 공백은 시스템이 처리합니다.(이하 동일 형식 Y/N로 안내)

- [Y 입력 시]
    - 고객에게 프로모션 적용을 위해 필요한 수량만큼 상품을 더 가져올 수 있도록 안내합니다.
    - 고객이 추가 상품을 가져오면 프로모션 가격으로 결제를 진행합니다.
- [N 입력 시]
    - 무료 증정 상품을 추가하지 않더라도 그대로 결제를 진행합니다.

- 만약 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 고객에게 묻고 요청에 따라 Y/N로 입력합니다.

`현재 {상품명}{수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)`

- [Y 입력 시]
  - 해당 수량에 대해 정가로 결제를 진행합니다.
- [N 입력 시] 주의 ⛔️

  **- 구매하지 않겠다고 입력할 시에는 해당 수량을 빼고 "나머지 수량"으로 결제를 진행합니다.**


---

## 멤버십 할인 관련 안내

- 멤버십 할인 적용 여부를 고객에게 확인하고 요청에 따라 Y/N 형식으로 입력합니다.


`멤버십 할인을 받으시겠습니까? (Y/N)`


- 멤버십 할인율은 본사 정책에 따라 "30%로 고정되어 있으며" 할인받을 수 있는 할인의 최대 한도는 8,000원입니다.
- 즉 할인 금액이 8,000을 넘을 수 없습니다.
- 예를 들어 30,000원이 최종 결제 금액이고 멤버십 할인을 받고자 하더라도 30,000의 30%는 9,000원이니 8,000원까지만 할인을 받을 수 있는 거죠!
  - 즉 최종 결제 금액은 22,000원이 됩니다.
<br>
- 관련 사항은 고객님에게 잘 안내해드리기 바랍니다 ☺️
<br>
### ⁇ 그럼 만약 프로모션 할인을 받았다면 멤버십 할인은 어떻게 될까요 ⁇

### ⛔️언제나 프로모션 미적용 금액에만 멤버십 할인을 받을 수 있습니다⛔️

`==============W 편의점================

상품명	수량	금액

콜라	3	3,000

에너지바	5	10,000

=============증      정===============

콜라	 1

총구매액	  8	    13,000 <br>
행사할인			-1,000 <br>
멤버십할인			-3,000 <br>
내실돈			 9,000 <br>`

- 프로모션 적용 후 ‘남은 금액’에 대해서만 30%의 멤버십 할인을 적용합니다!
- 즉 위 예시에서 콜라는 2+1 프로모션 상품으로 3,000원에 대한 할인이 반영된 것이기 때문에 "3,000원"은 멤버십 할인 대상 금액에서는 제외됩니다.
- 그럼 나머지 10,000원에 대해 30% 멤버십 할인이 진행될테니 총 3,000원의 멤버십 할인을 받을 수 있겠죠!!

<br>

그럼 아래 경우는 어떨까요?

==============W 편의점================<br>
상품명	수량	금액<br>
콜라	10	10,000 <br>

물      5      2,500<br>
=============증	            정===============<br>
콜라         	2

총구매액		15	12,500<br>
행사할인			-2,000<br>
멤버십할인			-1,950<br>
내실돈			 8,550<br>

- 이 경우는 콜라의 2+1 프로모션 수량이 7개밖에 없다고 가정해봅시다.
- 총 6개의 콜라(2+1, 2+1)에 대해 프로모션 할인 2,000원이 적용되었습니다.
- 그렇다면 나머지 4개의 콜라에는 프로모션 할인이 적용되지 않았기 때문에 4,000원에 대해서는 멤버십 할인을 받을 수 있습니다!
- 물은 프로모션이 적용되지 않으니 당연히 할인이 적용되겠죠?
- 즉 4,000 + 2,500 = 6,500원에 대해 30%의 할인을 받아 1,950원을 멤버십 할인을 받을 수 있습니다.
- 그럼 총 결제 금액은 8,550이 됩니다.


다른 예시를 볼까요?

==============W 편의점================<br>
상품명	   수량	금액<br>
오렌지주스	    2	       3,600<br>
=============증  	정===============<br>
오렌지주스	       1<br>

총구매액		2	3,600<br>
행사할인			-1,800<br>
멤버십할인			-0<br>
내실돈			 1,800<br>

- 이때는 오렌지 주스 2개에 대한 프로모션이 적용되었으니 멤버십 할인을 적용하더라도 할인 금액은 0이겠죠?<br>


결제가 완료되면 총구매액, 행사할인 금액, 멤버십 할인 금액, 최종적으로 낼 돈을 포함한 영수증이 출력됩니다🧾<br>

그렇게 구매가 완료되면 구매하고 싶은 다른 상품이 있는지 묻는 요청에 고객 요청에 따라 값을 입력하시면 됩니다. 아래 예시처럼요!

`감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)`

- [Y 입력]: 처음부터 다시 결제를 진행합니다.
- [N 입력]: 결제 프로그램이 종료됩니다.

<br>

### 주의: 모든 입력값은 정해진 형식을 준수하지 않으면 잘못된 입력 시점부터 다시!! 입력받아야 합니다.


<br>

### 🎊다시 한번 편의점 개업을 축하드리며 번창하시길 바랍니다🎊

 <br>

### P.S. 문의사항은 본사에 직접 문의해주세요

