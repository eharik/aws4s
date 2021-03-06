package org.aws4s.sqs

import cats.implicits._
import org.aws4s.core.ParamValidator

case class ReceiptHandle(raw: String)
    extends SqsParam[String](
      "ReceiptHandle",
      ParamValidator.noValidation
    )
