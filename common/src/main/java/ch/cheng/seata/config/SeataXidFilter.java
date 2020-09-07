package ch.cheng.seata.config;

import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Cheng Mingwei
 * @create 2020-09-03 18:10
 **/
@Component
@Log4j2
public class SeataXidFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();

        String restXid = request.getHeaders().getFirst("xid");
        if (StringUtils.isNotBlank(restXid)) {
            RootContext.bind(restXid);
            if (log.isDebugEnabled()) {
                log.debug("bind[" + restXid + "] to RootContext");
            }
        }
        return webFilterChain.filter(serverWebExchange.mutate().request(request).build());
    }
}

