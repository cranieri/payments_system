package domain

sealed trait PaymentProcessingError {
  val message: String
}

case class EmailSendError(message: String) extends PaymentProcessingError

case class AmountInvalidError(message: String) extends PaymentProcessingError

case class CurrencyInvalidError(message: String) extends PaymentProcessingError
