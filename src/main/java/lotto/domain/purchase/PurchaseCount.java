package lotto.domain.purchase;

import java.util.Objects;

public class PurchaseCount {
    private static final int MINIMUM_COUNT = 0;
    private static final String TILDE = "~";
    private static final String MSG_PURCHASE = "사이로 구매 할 수 있습니다.";

    private final int manualCount;
    private final int autoCount;

    private PurchaseCount(PurchaseAmount purchaseAmount, int manualCount) {
        checkManualCount(purchaseAmount, manualCount);
        this.manualCount = manualCount;
        this.autoCount = purchaseAmount.purchaseTotalQuantity() - manualCount;
    }

    private void checkManualCount(PurchaseAmount purchaseAmount, int manualCount) {
        if (manualCount < 0 || manualCount > purchaseAmount.purchaseTotalQuantity()) {
            throw new InvalidPurchaseCountException(MINIMUM_COUNT + TILDE +
                    purchaseAmount.purchaseTotalQuantity() + MSG_PURCHASE);
        }
    }

    public static PurchaseCount of(PurchaseAmount purchaseAmount, int manualCount) {
        return new PurchaseCount(purchaseAmount, manualCount);
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseCount that = (PurchaseCount) o;
        return manualCount == that.manualCount &&
                autoCount == that.autoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualCount, autoCount);
    }
}
