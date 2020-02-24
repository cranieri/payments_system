package interpreters

import algebras.EmailSenderAlg
import cats.data.EitherT
import cats.effect.Sync
import domain.{PaymentProcessingError, PaymentStatus, ReadyToSubmit, Submitted}

class EmailSender[F[_]: Sync] extends EmailSenderAlg[F]{
  def send[T <: PaymentStatus](paymentStatus: T): EitherT[F, PaymentProcessingError, Unit] =
    EitherT[F, PaymentProcessingError, Unit](Sync[F].pure(Right(println(s"send email to ${emailRecipients(paymentStatus)}"))))

  private def emailRecipients(paymentStatus: PaymentStatus): String = {
    paymentStatus match {
      case _: ReadyToSubmit.type => "product@email.com"
      case _: Submitted.type => "ops@email.com"
    }
  }
}
