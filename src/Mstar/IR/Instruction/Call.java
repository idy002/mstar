package Mstar.IR.Instruction;

import Mstar.IR.BasicBlock;
import Mstar.IR.Function;
import Mstar.IR.IIRVisitor;
import Mstar.IR.Operand.*;
import Mstar.Worker.BackEnd.IRBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Call extends IRInstruction {
    public Address dest;
    public Function func;
    public LinkedList<Operand> args;

    private void addCalleeFunction() {
        Function caller = super.bb.function;
        caller.callee.add(func);
    }
    public Call(BasicBlock bb, Address dest, Function func, Operand... args) {
        super(bb);
        this.dest = dest;
        this.func = func;
        this.args = new LinkedList<>(Arrays.asList(args));
        addCalleeFunction();
    }
    public Call(BasicBlock bb, Address dest, Function func, LinkedList<Operand> args) {
        super(bb);
        this.dest = dest;
        this.func = func;
        this.args = new LinkedList<>(args);
        addCalleeFunction();
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        return new LinkedList<>(Arrays.asList(IRBuilder.vargRegs).subList(0, Integer.min(6, args.size())));
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        regs.add(IRBuilder.vrax);
        return regs;
    }

    @Override
    public LinkedList<StackSlot> getStackSlots() {
        LinkedList<StackSlot> slots = new LinkedList<>();
        slots.addAll(defaultGetStackSlots(dest));
        for(Operand operand : args) {
            if(operand instanceof StackSlot)
                slots.add((StackSlot) operand);
        }
        return slots;
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if(dest instanceof Register && renameMap.containsKey(dest))
            dest = renameMap.get(dest);
    }


    @Override
    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}
