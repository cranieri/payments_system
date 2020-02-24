package interpreters

import algebras.PaymentSubmitterAlg
import domain.{Barclays, BarclaysSubmitter, NatWest, NatWestSubmitter, ValidPayment}

class PaymentSubmitter extends PaymentSubmitterAlg {
  def submit(payment: ValidPayment): Unit = {
    payment.bankProvider match {
      case Barclays => BarclaysSubmitter.submit
      case NatWest => NatWestSubmitter.submit
    }
  }
}