package algebras

import domain.{PaymentData, PaymentProcessingError, ValidPayment}

trait PaymentValidatorAlg {
  def validate(payment: PaymentData): Either[PaymentProcessingError, ValidPayment]
}
