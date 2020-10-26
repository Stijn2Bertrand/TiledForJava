package drawloop;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

public interface MouseInterface {

    default Optional<BiConsumer<int[], MouseEvent>> getMouseClickedListener(){
        return Optional.empty();
    }

    default Optional<BiConsumer<int[],MouseEvent>> getMouseMovedListener(){
        return Optional.empty();
    }

}
