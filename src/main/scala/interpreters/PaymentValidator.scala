package interpreters

import algebras.PaymentValidatorAlg
import domain.{AmountInvalidError, BankProvider, Barclays, CurrencyInvalidError, NatWest, PaymentData, PaymentProcessingError, ValidPayment}

class PaymentValidator extends PaymentValidatorAlg {
  def validate(payment: PaymentData): Either[PaymentProcessingError, ValidPayment] = {
    if (payment.amount < 1) {
      return Left(AmountInvalidError("invalid amount"))
    }

    if (payment.currency != payment.beneficiary.currency) {
      return Left(CurrencyInvalidError("invalid amount"))
    }

    Right(ValidPayment(
      payment.amount,
      payment.currency,
      payment.paymentReason,
      payment.beneficiary,
      getProvider(payment.currency)
    ))
  }

  private def getProvider(currency: String): BankProvider = {
    if (currency == "GBP") Barclays else NatWest
  }
}