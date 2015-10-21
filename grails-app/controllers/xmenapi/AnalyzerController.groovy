package xmenapi
import xmenapi.utils.MutantAnalyzer

class AnalyzerController {
    static allowedMethods = [index:'POST']
    def mutantAnalyzer = MutantAnalyzer.getInstance();

    def index() {
        def jsonRequest = request.JSON
        def jsonResponse = mutantAnalyzer.isMutant(jsonRequest as String[])
        jsonResponse = jsonResponse.toString()
        render(contentType: 'text/json') {[
                'results': jsonResponse,
                'status': jsonResponse ? "OK" : "Nothing present"
        ]}
    }
}
