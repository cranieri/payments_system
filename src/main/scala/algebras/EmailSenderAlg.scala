package algebras

import cats.data.EitherT
import domain.{PaymentProcessingError, PaymentStatus}

trait EmailSenderAlg[F[_]] {
  def send[T <: PaymentStatus](paymentStatus: T): EitherT[F, PaymentProcessingError, Unit]
}
