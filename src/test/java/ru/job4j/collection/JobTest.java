package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {

    @Test
    public void whenComparatorByName() {
        Comparator<Job> cmpName = new SortByNameJob();
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 5),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameDesc() {
        Comparator<Job> cmpName = new SortDescByNameJob();
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Reboot server", 1),
                new Job("Impl task", 5),
                new Job("Impl task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByPriority() {
        Comparator<Job> cmpName = new SortByPriorityJob();
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Impl task", 0),
                new Job("Fix bug", 1),
                new Job("Reboot server", 1),
                new Job("Fix bug", 2),
                new Job("Fix bug", 4),
                new Job("Impl task", 5)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByPriorityDesc() {
        Comparator<Job> cmpName = new SortDescByPriorityJob();
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Fix bug", 1),
                new Job("Reboot server", 1),
                new Job("Impl task", 0)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameAndPriorityList() {
        Comparator<Job> cmpName = new SortByNameJob().thenComparing(new SortByPriorityJob());
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Fix bug", 4),
                new Job("Impl task", 0),
                new Job("Impl task", 5),
                new Job("Reboot server", 1)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameAndPriorityDesc() {
        Comparator<Job> cmpName = new SortByNameJob().thenComparing(new SortDescByPriorityJob());
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameDescAndPriority() {
        Comparator<Job> cmpName = new SortDescByNameJob().thenComparing(new SortByPriorityJob());
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Reboot server", 1),
                new Job("Impl task", 0),
                new Job("Impl task", 5),
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Fix bug", 4)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameDescAndPriorityDesc() {
        Comparator<Job> cmpName = new SortDescByNameJob().thenComparing(new SortDescByPriorityJob());
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 5),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Impl task", 0),
                new Job("Reboot server", 1)
        );
        List<Job> sortJobs = Arrays.asList(
                new Job("Reboot server", 1),
                new Job("Impl task", 5),
                new Job("Impl task", 0),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Fix bug", 1)
        );
        Collections.sort(jobs, cmpName);
        assertThat(jobs).isEqualTo(sortJobs);
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new SortDescByNameJob().thenComparing(new SortDescByPriorityJob());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

}