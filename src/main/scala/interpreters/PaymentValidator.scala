package interpreters

import algebras.PaymentValidatorAlg
import domain.{AmountInvalidError, Barclays, CurrencyInvalidError, NatWest, PaymentData, PaymentProcessingError, ValidPayment}

class PaymentValidator extends PaymentValidatorAlg {
  def validate(payment: PaymentData): Either[PaymentProcessingError, ValidPayment] = {
    if (payment.amount < 1) {
      return Left(AmountInvalidError("invalid amount"))
    }

    if (payment.currency != payment.beneficiary.currency) {
      return Left(CurrencyInvalidError("invalid amount"))
    }

    if (payment.currency == "GBP") {
      Right(ValidPayment(
        payment.amount,
        payment.currency,
        payment.paymentReason,
        payment.beneficiary,
        Barclays
      ))
    } else {
      Right(ValidPayment(
        payment.amount,
        payment.currency,
        payment.paymentReason,
        payment.beneficiary,
        NatWest)
      )
    }
  }
}