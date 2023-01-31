/**
 *
package ca.jrvs.apps.trading.controller;


import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@Api(value = "quote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
@RequestMapping("quote")
public class QuoteController {

  private QuoteService quoteService;

  @Autowired
  public QuoteController(QuoteService quoteService){
    this.quoteService = quoteService;
  }

  //@ApiOperation(value = "Show iexQuote", notes = "Show iexQuote for a given ticker/symbol")
  //@ApiResponses(value = {@ApiResponse(code = 404, message = "ticker is not found")})
  @GetMapping(path = "/iex/ticker/{ticker}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public IexQuote getQuote(@PathVariable String ticker){
    try {
      return quoteService.findIexQuoteByTicker(ticker);
    } catch (Exception e){
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

}
*/