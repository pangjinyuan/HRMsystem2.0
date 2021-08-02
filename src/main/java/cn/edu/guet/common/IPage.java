package cn.edu.guet.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPage {
    private Long page;
    private Long limit;
    private Long start;
    private Long end;

    public IPage(Long page, Long limit) {
        this.page = page;
        this.limit = limit;
    }

    public IPage config() {
        this.start = (page - 1) * limit;
        this.end = page * limit + 1;
        return this;
    }
}
