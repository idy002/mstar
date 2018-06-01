package Mstar.IR;

import Mstar.IR.Operand.PhysicalRegister;

import java.util.LinkedList;

public class X86RegisterSet {
    public static PhysicalRegister rax;
    public static PhysicalRegister rcx;
    public static PhysicalRegister rdx;
    public static PhysicalRegister rbx;
    public static PhysicalRegister rsp;
    public static PhysicalRegister rbp;
    public static PhysicalRegister rsi;
    public static PhysicalRegister rdi;
    public static PhysicalRegister r8;
    public static PhysicalRegister r9;
    public static PhysicalRegister r10;
    public static PhysicalRegister r11;
    public static PhysicalRegister r12;
    public static PhysicalRegister r13;
    public static PhysicalRegister r14;
    public static PhysicalRegister r15;

    public static LinkedList<PhysicalRegister> regs;
    public static LinkedList<PhysicalRegister> callerSave;
    public static LinkedList<PhysicalRegister> calleeSave;
    public static LinkedList<PhysicalRegister> args;

    public static void init() {
        regs = new LinkedList<>();
        calleeSave = new LinkedList<>();
        callerSave = new LinkedList<>();
        args = new LinkedList<>();
        String[] names = new String[]{
                "rax", "rcx", "rdx", "rbx", "rsp", "rbp", "rsi", "rdi", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15",
        };
        Boolean[] isCallerSave = new Boolean[]{
                true,  true,  true,  false, null,  null, true,  true,  true, true, true,  true,  false, false, false, false
        };
        for (int i = 0; i < 16; i++) {
            PhysicalRegister pr = new PhysicalRegister();
            regs.add(pr);
            pr.name = names[i];
            if(isCallerSave[i] != null) {
                pr.isCallerSave = isCallerSave[i];
                pr.isCalleeSave = !isCallerSave[i];
                if(isCallerSave[i])
                    callerSave.add(pr);
                else
                    calleeSave.add(pr);
            }

        }
        rax = regs.get(0);
        rcx = regs.get(1);
        rdx = regs.get(2);
        rbx = regs.get(3);
        rsp = regs.get(4);
        rbp = regs.get(5);
        rsi = regs.get(6);
        rdi = regs.get(7);
        r8 = regs.get(8);
        r9 = regs.get(9);
        r10 = regs.get(10);
        r11 = regs.get(11);
        r12 = regs.get(12);
        r13 = regs.get(13);
        r14 = regs.get(14);
        r15 = regs.get(15);
        args.add(rdi);
        args.add(rsi);
        args.add(rdx);
        args.add(rcx);
        args.add(r8);
        args.add(r9);
    }
}
