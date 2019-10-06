package register;

/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent memory address register
 */
public class MemoryAddressRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.MEMORY_ADDRESS_REGISTER;
    }
}