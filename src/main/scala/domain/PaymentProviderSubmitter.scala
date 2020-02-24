package domain

trait PaymentProviderSubmitter {
  def submit: Unit
}

object BarclaysSubmitter extends PaymentProviderSubmitter {
  def submit: Unit = println("submitted via Barclays")
}

object NatWestSubmitter extends PaymentProviderSubmitter {
  def submit: Unit = println("submitted via NatWest")
}