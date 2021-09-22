package by.barilo.objects;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class MenuItem {
    private String id;
    private String title;
    private String parent;
    private Module module;
}
