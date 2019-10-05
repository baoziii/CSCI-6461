package instruction;

import memory.Address;
import memory.Cache;
import register.GeneralPurposeRegister;
import register.IndexRegister;

public class AddressDecoder {

    protected static Address decodeAddress(Instruction instruction) {
        GeneralPurposeRegister generalPurposeRegister = instruction.getGeneralPurposeRegister();
        IndexRegister indexRegister = instruction.getIndexRegister();
        InstructionType type = instruction.getType();
        boolean indirect = instruction.isIndirect();
        int effectiveAddressInDecimal = instruction.getEffectiveAddressInDecimal();

        if (type == InstructionType.AIR || type == InstructionType.SIR) {
            return new Address(effectiveAddressInDecimal);
        }

        if (indexRegister == null) {
            if (type == InstructionType.LDX || type == InstructionType.STX) {
                throw new IllegalArgumentException("Invalid Index Register");
            }
        } else if (generalPurposeRegister != null) {
            effectiveAddressInDecimal += indexRegister.getDecimalValue();
        }

        if (indirect) {
            effectiveAddressInDecimal = Cache.getWordDecimalValue(new Address(effectiveAddressInDecimal));
        }

        return new Address(effectiveAddressInDecimal);
    }
}
