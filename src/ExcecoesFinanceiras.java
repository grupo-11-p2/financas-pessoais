public class ExcecoesFinanceiras {

    public static class ArquivoDadosException extends RuntimeException {
        public ArquivoDadosException(String mensagem) {
            super(mensagem);
        }

        public ArquivoDadosException(String mensagem, Throwable causa) {
            super(mensagem, causa);
        }
    }

    public static class ValorInvalidoException extends IllegalArgumentException {
        public ValorInvalidoException(String mensagem) {
            super(mensagem);
        }

        public ValorInvalidoException(String mensagem, Throwable causa) {
            super(mensagem, causa);
        }
    }

    public static class SaldoInsuficienteException extends IllegalArgumentException {
        public SaldoInsuficienteException(String mensagem) {
            super(mensagem);
        }

        public SaldoInsuficienteException(String mensagem, Throwable causa) {
            super(mensagem, causa);
        }
    }

    public static class ContaNaoEncontradaException extends IllegalArgumentException {
        public ContaNaoEncontradaException(String mensagem) {
            super(mensagem);
        }

        public ContaNaoEncontradaException(String mensagem, Throwable causa) {
            super(mensagem, causa);
        }
    }
}