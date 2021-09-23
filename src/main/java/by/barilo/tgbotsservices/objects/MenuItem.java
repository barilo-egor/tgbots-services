package by.barilo.tgbotsservices.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private String id;
    private String title;
    private String parent;
    private Module module;
}
