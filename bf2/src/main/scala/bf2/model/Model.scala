package bf2.model

import com.raquo.laminar.api.L.Var
final class Model {
  val characterVar: Var[CharacterClass] = ???
  val characterSignal = characterVar.signal
}
