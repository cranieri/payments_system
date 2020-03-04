package domain

case class Beneficiary(name: String, last_name: String, currency: String)

sealed trait BankProvider

case object Barclays extends BankProvider

case object NatWest extends BankProvider

sealed trait Payment {
  val amount: Int
  val currency: String
  val beneficiary: Beneficiary
}

case class PaymentData(
                        amount: Int,
                        currency: String,
                        paymentReason: String,
                        beneficiary: Beneficiary
                      ) extends Payment

case class ValidPayment(
                         amount: Int,
                         currency: String,
                         paymentReason: String,
                         beneficiary: Beneficiary,
                         bankProvider: BankProvider
                       ) extends Payment