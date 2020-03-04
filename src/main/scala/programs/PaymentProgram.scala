package programs

import algebras.{EmailSenderAlg, PaymentSubmitterAlg, PaymentValidatorAlg}
import cats.data.EitherT
import cats.effect.Sync
import domain.{PaymentData, PaymentProcessingError, ReadyToSubmit, Submitted, ValidPayment}
import interpreters.{PaymentSubmitter, PaymentValidator}
import cats.implicits._

class PaymentProgram[F[_]: Sync](payment: PaymentData,
                                 paymentValidator: PaymentValidatorAlg,
                                 paymentSubmitter: PaymentSubmitterAlg,
                                 emailSender: EmailSenderAlg[F]) {
  def processPayment: F[Unit] = {
    val processedPayment = for {
      validPayment <- EitherT[F, PaymentProcessingError, ValidPayment](Sync[F].pure(paymentValidator.validate(payment)))
      _ <- emailSender.send(ReadyToSubmit)
      submittedPayment = paymentSubmitter.submit(validPayment)
      _ <- emailSender.send(Submitted)
    } yield submittedPayment


    processedPayment.value.map {
      payment =>
        payment match {
          case Right(_) => println(s"Payment successfully submitted")
          case Left(_) => println("Error while processing the payment")
        }
    }
  }
}