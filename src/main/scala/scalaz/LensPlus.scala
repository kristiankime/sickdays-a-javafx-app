package scalaz

import scalaz._
import scalaz.Lens._

/**
 * Lenses are required to satisfy the following two laws and to be side-effect free
 *
 * forall b. lens(lens.set(a,b)) = b
 * forall a. lens.set(a,lens(a)) = a
 *
 * https://blog.stackmob.com/2012/02/an-introduction-to-lenses-in-scalaz/
 * 
 * http://hpaste.org/41832/lenses_for_scalaz
 * 
 * The following links contain more information about lenses
 * 
 * http://stackoverflow.com/questions/3900307/cleaner-way-to-update-nested-structures
 * http://www.youtube.com/watch?v=efv0SQNde5Q&ytsession=Q9Z6Ae4sJffYJoOvcxG9H9WfzWB4iREgfyaTrXt5yKm26_uvWqrQKTMCmQu903qgkJXwaFv0IooaUlL8fxVrwV9D0bRgghrbKwUpAb99Ul5Hx6l4O69CGzISmJxe65enlt0ERAkdD_vXDkxsw7E3n-lyc1hchJBFuhNnCRhNNfkar23WrlqBtK07FKZ-7ORyzJY-8RK-lEBwdqAAAPmJho2plO_AUccmolPrLMHye1Y
 */
object LensPlus {
	// This is a "fix" for scalaz.Lens.arrayLens
	implicit def arrayLensTypeFix[S, A] = ArrayLens[S, A](_)
}