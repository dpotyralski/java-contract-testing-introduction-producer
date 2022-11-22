package pl.dotyralski.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RiskVerifyController {

    private static final int MAX_BIDS = 10;
    private static final String MICROSOFT = "Microsoft";

    @PostMapping(path = "/risks")
    public ResponseEntity<RiskValidateResponse> validateRisk(@Valid @RequestBody RiskValidateRequest request) {
        if (isRequestSafe(request)) {
            return ResponseEntity.ok(new RiskValidateResponse(Status.NOT_OK));
        }
        return ResponseEntity.ok(new RiskValidateResponse(Status.OK));
    }

    private boolean isRequestSafe(RiskValidateRequest request) {
        return MICROSOFT.equals(request.getCompany()) && request.getAmount() > MAX_BIDS;
    }


}