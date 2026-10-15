
package com.cosmoquest.app.domain

import java.math.BigDecimal
import java.math.RoundingMode

object CommissionCalculator {

    private const val BASIS_POINTS_PER_WHOLE = 10_000

    data class CommissionResult(
        val saleAmountPaise: Long,
        val commissionPaise: Long,
        val sellerAmountPaise: Long
    )

    /**
     * Calculates commission using basis points.
     * 1,000 basis points = 10%.
     * All monetary amounts are represented in paise.
     */
    fun calculate(
        saleAmountPaise: Long,
        commissionBasisPoints: Int
    ): CommissionResult {
        require(saleAmountPaise >= 0) {
            "Sale amount cannot be negative."
        }
        require(commissionBasisPoints in 0..BASIS_POINTS_PER_WHOLE) {
            "Commission must be between 0% and 100%."
        }

        val commission = BigDecimal.valueOf(saleAmountPaise)
            .multiply(BigDecimal.valueOf(commissionBasisPoints.toLong()))
            .divide(
                BigDecimal.valueOf(BASIS_POINTS_PER_WHOLE.toLong()),
                0,
                RoundingMode.HALF_UP
            )
            .longValueExact()

        return CommissionResult(
            saleAmountPaise = saleAmountPaise,
            commissionPaise = commission,
            sellerAmountPaise = saleAmountPaise - commission
        )
    }

    fun formatINR(amountPaise: Long): String {
        require(amountPaise >= 0) {
            "Amount cannot be negative."
        }

        val rupees = BigDecimal.valueOf(amountPaise)
            .divide(BigDecimal.valueOf(100))

        return "₹" + rupees.setScale(2, RoundingMode.UNNECESSARY)
            .toPlainString()
    }
}
