package domain

sealed trait PaymentStatus

case object ReadyToSubmit extends PaymentStatus

case object Submitted extends PaymentStatus
