package org.aws4s.s3

import cats.effect.Effect
import org.aws4s._
import org.http4s.{Method, Request, Uri}
import fs2.Stream

private [aws4s] case class PutObject[F[_]: Effect](region: Region, bucket: Bucket, name: Uri.Path, obj: Stream[F, Byte], payloadSigning: PayloadSigning) extends ParamlessCommand[F, Unit] {

  override def request(credentials: () => Credentials): F[Request[F]] =
    ObjectRequests(credentials, region, bucket, name)
      .request(Method.PUT, payloadSigning, obj)

  override def trySuccessResponse(response: ResponseContent): Option[Unit] =
    response tryParse {
      case NoContent => Some(())
    }
}