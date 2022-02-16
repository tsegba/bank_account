package bank.api.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Statement {
    private final List<OperationLine> operationLines;

    public Statement(List<OperationLine> operationLines) {
        this.operationLines = operationLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return Objects.equals(operationLines, statement.operationLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationLines);
    }

    public List<OperationLine> getOperationLines() {
        if (this.operationLines == null) return new ArrayList<OperationLine>();
        return this.operationLines.stream()
                .sorted(Comparator.comparing(OperationLine::getOperationDate).reversed()).collect(Collectors.toList());
    }

}
