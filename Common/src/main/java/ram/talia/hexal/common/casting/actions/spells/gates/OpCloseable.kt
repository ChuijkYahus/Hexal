package ram.talia.hexal.common.casting.actions.spells.gates

import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import ram.talia.hexal.api.getGate

object OpCloseable : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val gate = args.getGate(0, argc)

        if (gate.isDrifting) {
            return true.asActionResult
        } else {
            val targetPos = gate.getTargetPos(env.world) ?: return false.asActionResult
            return env.isVecInWorld(targetPos.subtract(0.0, 1.0, 0.0)).asActionResult
        }
    }
}