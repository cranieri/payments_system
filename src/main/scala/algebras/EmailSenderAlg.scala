package algebras

import cats.data.EitherT
import domain.{PaymentProcessingError, PaymentStatus}

trait EmailSenderAlg[F[_]] {
  def send(paymentStatus: PaymentStatus): EitherT[F, PaymentProcessingError, Unit]
}
