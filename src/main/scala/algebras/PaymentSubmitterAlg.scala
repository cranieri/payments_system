package algebras

import domain.ValidPayment

trait PaymentSubmitterAlg {
  def submit(payment: ValidPayment): Unit
}
