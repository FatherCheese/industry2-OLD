package turniplabs.industry.interfaces

interface IMachineCondition {
    val getMachineHealth: Int

    fun setMachineHealth(newHealth: Int)
}