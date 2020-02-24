import cats.effect.IO
import domain.{Beneficiary, PaymentData}
import interpreters.{EmailSender, PaymentSubmitter, PaymentValidator}
import programs.PaymentProgram

object Main extends App {
  val paymentData = PaymentData(10,"GBP", "dinner",Beneficiary("John", "Doe","GBP"))

  val program = new PaymentProgram[IO](paymentData,new PaymentValidator, new PaymentSubmitter, new EmailSender)

  program.processPayment.unsafeRunSync

}
